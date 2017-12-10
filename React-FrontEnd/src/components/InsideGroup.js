import React, {Component} from 'react';
import PropTypes from 'prop-types';
import swal from 'sweetalert';
import axios from 'axios';

class InsideGroup extends Component {
    static propTypes = {
        message: PropTypes.string.isRequired
    };

    constructor(props) {
        super(props);
        this.state = {
            groupname: '',
            groupmembers:[]
        };
    }
    componentDidMount(){
        axios.create({withCredentials:true}).get('http://localhost:3001/groupmembers',
            {
                params: {
                    groupname: this.state.groupname
                }
            }).then((response) => {
            this.setState({
                groupmembers: response.data
            });
            console.log(this.state.groupmembers);
        }).catch((error) => {
            console.log("got errr while posting data", error);
        });
    }
    componentWillMount() {
        axios.create({withCredentials:true}).get('http://localhost:3001/groupmembers',
            {
                params: {
                    groupname: this.state.groupname
                }
            }).then((response) => {
            this.setState({
                groupmembers: response.data
            });
            console.log(this.state.groupmembers);
        }).catch((error) => {
            console.log("got errr while posting data", error);
        });
        this.setState({
            groupname:this.props.groupname
        });
    }


    deletememberfromgroup(name)
    {
        axios.create({withCredentials:true}).post('http://localhost:3001/deletememberfromgroup',
            {
                membername: name,
                groupname:this.state.groupname
            }).then((response) => {
            axios.create({withCredentials: true}).get('http://localhost:3001/groupmembers',
                {
                    params: {
                        groupname: this.state.groupname
                    }
                }).then((response1) => {
                this.setState({
                    groupmembers: response1.data
                });

            }).catch((error) => {
                console.log("got errr while posting data", error);
            });
        }).catch((error) => {
            console.log("got errr while posting data", error);
        });
    }



    addtogroup(){
        var name='';
        swal( "Enter Username:", {
            content:"input",
        })
            .then((value) => {
                name= `${value}`;
                swal(`New Member added to the Group`);
                axios.create({withCredentials:true}).post('http://localhost:3001/addmemberingroup',
                    {
                        name: name,
                        groupname:this.state.groupname
                    }).then((response) => {
                    axios.create({withCredentials: true}).get('http://localhost:3001/groupmembers',
                        {
                            params: {
                                groupname: this.state.groupname
                            }
                        }).then((response1) => {
                        this.setState({
                            groupmembers: response1.data
                        });

                    }).catch((error) => {
                        console.log("error", error);
                    });
                }).catch((error) => {
                    console.log("error", error);
                });
            });
    }

    render() {
        return (
            <div>
                <div className="col-md-12">
                    <div className="form-group">
                        <h2 className={'group_name'}>{this.state.groupname}</h2>
                    </div>
                    <div className="container-fluid"><div className={'acc_det'}> <button className="btn btn-primary" onClick={() => this.addtogroup()}>Add member</button></div>
                    </div>
                </div>


                <table className = "table table-hover">
                    <thead>
                    <tr>
                        <th>Group Members</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.groupmembers.map(tile =>
                        (
                            <tr>
                                <td className={'tablefield'}>
                                    <div className="form-group">
                                        {tile.username}
                                        <span> </span>
                                    </div>
                                </td>
                                <td className={'tablefield'}>
                                    <button className="btn btn-primary" onClick={() => this.deletememberfromgroup(tile.username)}>Remove</button>
                                </td>
                            </tr>
                        )
                    )}

                    </tbody>
                </table>
            </div>
        );
    }
}

export default InsideGroup;